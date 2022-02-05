import { Component, Vue, Inject } from 'vue-property-decorator';

import LocalDesejadoService from '@/entities/local-desejado/local-desejado.service';
import { ILocalDesejado } from '@/shared/model/local-desejado.model';

import TipoInformacaoService from '@/entities/tipo-informacao/tipo-informacao.service';
import { ITipoInformacao } from '@/shared/model/tipo-informacao.model';

import RecebaEmailService from '@/entities/receba-email/receba-email.service';
import { IRecebaEmail } from '@/shared/model/receba-email.model';

import TaskRecebaDadosC19Service from './task-receba-dados-c-19.service';
import { TaskRecebaDadosC19Context } from './task-receba-dados-c-19.model';

const validations: any = {
  taskContext: {
    informacoesProcess: {
      informacoes: {
        tipoDeInformacao: {},
        local: {},
        receberEmail: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskRecebaDadosC19ExecuteComponent extends Vue {
  private taskRecebaDadosC19Service: TaskRecebaDadosC19Service = new TaskRecebaDadosC19Service();
  private taskContext: TaskRecebaDadosC19Context = {};

  @Inject('localDesejadoService') private localDesejadoService: () => LocalDesejadoService;

  public localDesejados: ILocalDesejado[] = [];

  @Inject('tipoInformacaoService') private tipoInformacaoService: () => TipoInformacaoService;

  public tipoInformacaos: ITipoInformacao[] = [];

  @Inject('recebaEmailService') private recebaEmailService: () => RecebaEmailService;

  public recebaEmails: IRecebaEmail[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskRecebaDadosC19Service.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskRecebaDadosC19Service.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.localDesejadoService()
      .retrieve()
      .then(res => {
        this.localDesejados = res.data;
      });
    this.tipoInformacaoService()
      .retrieve()
      .then(res => {
        this.tipoInformacaos = res.data;
      });
    this.recebaEmailService()
      .retrieve()
      .then(res => {
        this.recebaEmails = res.data;
      });
  }
}
