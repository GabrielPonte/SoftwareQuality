import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskPreencherDadosService from './task-preencher-dados.service';
import { TaskPreencherDadosContext } from './task-preencher-dados.model';

@Component
export default class TaskPreencherDadosDetailsComponent extends Vue {
  private taskPreencherDadosService: TaskPreencherDadosService = new TaskPreencherDadosService();
  private taskContext: TaskPreencherDadosContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskPreencherDadosService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
