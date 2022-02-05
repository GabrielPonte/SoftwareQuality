import { Component, Vue, Inject } from 'vue-property-decorator';

import TipoInformacaoService from '@/entities/tipo-informacao/tipo-informacao.service';
import { ITipoInformacao } from '@/shared/model/tipo-informacao.model';

import TaskRecebaDadosC19Service from './task-receba-dados-c-19.service';
import { TaskRecebaDadosC19Context } from './task-receba-dados-c-19.model';

const validations: any = {
  taskContext: {
    informacoesProcess: {
      informacoes: {},
    },
  },
};

@Component({
  validations,
})
export default class TaskRecebaDadosC19ExecuteComponent extends Vue {
  private taskRecebaDadosC19Service: TaskRecebaDadosC19Service = new TaskRecebaDadosC19Service();
  private taskContext: TaskRecebaDadosC19Context = {};

  @Inject('tipoInformacaoService') private tipoInformacaoService: () => TipoInformacaoService;

  public tipoInformacaos: ITipoInformacao[] = [];
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
    this.tipoInformacaoService()
      .retrieve()
      .then(res => {
        this.tipoInformacaos = res.data;
      });
  }
}
