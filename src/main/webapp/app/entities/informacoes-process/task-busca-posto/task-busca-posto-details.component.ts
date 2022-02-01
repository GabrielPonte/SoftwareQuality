import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskBuscaPostoService from './task-busca-posto.service';
import { TaskBuscaPostoContext } from './task-busca-posto.model';

@Component
export default class TaskBuscaPostoDetailsComponent extends Vue {
  private taskBuscaPostoService: TaskBuscaPostoService = new TaskBuscaPostoService();
  private taskContext: TaskBuscaPostoContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskBuscaPostoService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
