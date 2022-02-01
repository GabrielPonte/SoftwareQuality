import { Component, Vue, Inject } from 'vue-property-decorator';

import PreencherDadosService from './preencher-dados.service';
import { PreencherDadosContext } from './preencher-dados.model';

const validations: any = {
  taskContext: {
    agendamentoProcess: {
      agendamento: {
        nomeCompleto: {},
        idade: {},
        CPF: {},
        CEP: {},
        endereco: {},
        complemento: {},
        qtdVacinas: {},
        sintomas: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class PreencherDadosExecuteComponent extends Vue {
  private preencherDadosService: PreencherDadosService = new PreencherDadosService();
  private taskContext: PreencherDadosContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.preencherDadosService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.preencherDadosService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
