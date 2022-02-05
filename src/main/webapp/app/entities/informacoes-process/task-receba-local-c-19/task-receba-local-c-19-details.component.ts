import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskRecebaLocalC19Service from './task-receba-local-c-19.service';
import { TaskRecebaLocalC19Context } from './task-receba-local-c-19.model';

@Component
export default class TaskRecebaLocalC19DetailsComponent extends Vue {
  private taskRecebaLocalC19Service: TaskRecebaLocalC19Service = new TaskRecebaLocalC19Service();
  private taskContext: TaskRecebaLocalC19Context = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskRecebaLocalC19Service.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
