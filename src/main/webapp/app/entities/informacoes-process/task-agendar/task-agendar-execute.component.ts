import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskAgendarService from './task-agendar.service';
import { TaskAgendarContext } from './task-agendar.model';

const validations: any = {
  taskContext: {
    informacoesProcess: {
      informacoes: {
        data: {},
        hora: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskAgendarExecuteComponent extends Vue {
  private taskAgendarService: TaskAgendarService = new TaskAgendarService();
  private taskContext: TaskAgendarContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskAgendarService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskAgendarService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
