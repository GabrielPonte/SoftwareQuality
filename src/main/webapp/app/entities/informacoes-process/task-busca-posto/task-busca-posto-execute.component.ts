import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskBuscaPostoService from './task-busca-posto.service';
import { TaskBuscaPostoContext } from './task-busca-posto.model';

const validations: any = {
  taskContext: {
    informacoesProcess: {
      informacoes: {
        cidade: {},
        estado: {},
        nomePosto: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskBuscaPostoExecuteComponent extends Vue {
  private taskBuscaPostoService: TaskBuscaPostoService = new TaskBuscaPostoService();
  private taskContext: TaskBuscaPostoContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskBuscaPostoService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskBuscaPostoService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
