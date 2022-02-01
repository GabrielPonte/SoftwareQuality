import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskRecebaDadosC19Service from './task-receba-dados-c-19.service';
import { TaskRecebaDadosC19Context } from './task-receba-dados-c-19.model';

@Component
export default class TaskRecebaDadosC19DetailsComponent extends Vue {
  private taskRecebaDadosC19Service: TaskRecebaDadosC19Service = new TaskRecebaDadosC19Service();
  private taskContext: TaskRecebaDadosC19Context = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskRecebaDadosC19Service.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
