import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskRecebaEmailC19Service from './task-receba-email-c-19.service';
import { TaskRecebaEmailC19Context } from './task-receba-email-c-19.model';

@Component
export default class TaskRecebaEmailC19DetailsComponent extends Vue {
  private taskRecebaEmailC19Service: TaskRecebaEmailC19Service = new TaskRecebaEmailC19Service();
  private taskContext: TaskRecebaEmailC19Context = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskRecebaEmailC19Service.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
