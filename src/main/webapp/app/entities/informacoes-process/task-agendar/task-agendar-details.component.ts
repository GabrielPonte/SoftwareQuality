import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskAgendarService from './task-agendar.service';
import { TaskAgendarContext } from './task-agendar.model';

@Component
export default class TaskAgendarDetailsComponent extends Vue {
  private taskAgendarService: TaskAgendarService = new TaskAgendarService();
  private taskContext: TaskAgendarContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskAgendarService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
