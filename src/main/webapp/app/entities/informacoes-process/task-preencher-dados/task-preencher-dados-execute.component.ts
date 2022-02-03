import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskPreencherDadosService from './task-preencher-dados.service';
import { TaskPreencherDadosContext } from './task-preencher-dados.model';

const validations: any = {
  taskContext: {
    informacoesProcess: {
      informacoes: {
        nomeCompleto: {},
        email: {},
        idade: {},
        cpf: {},
        cep: {},
        estado: {},
        cidade: {},
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
export default class TaskPreencherDadosExecuteComponent extends Vue {
  private taskPreencherDadosService: TaskPreencherDadosService = new TaskPreencherDadosService();
  private taskContext: TaskPreencherDadosContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskPreencherDadosService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskPreencherDadosService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
