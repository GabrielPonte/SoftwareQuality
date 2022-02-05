import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskInitialOptionService from './task-initial-option.service';
import { TaskInitialOptionContext } from './task-initial-option.model';

@Component
export default class TaskInitialOptionDetailsComponent extends Vue {
  private taskInitialOptionService: TaskInitialOptionService = new TaskInitialOptionService();
  private taskContext: TaskInitialOptionContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskInitialOptionService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
