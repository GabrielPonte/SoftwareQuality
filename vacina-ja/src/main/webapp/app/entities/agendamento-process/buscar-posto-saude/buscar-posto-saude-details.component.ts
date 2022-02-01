import { Component, Vue, Inject } from 'vue-property-decorator';

import BuscarPostoSaudeService from './buscar-posto-saude.service';
import { BuscarPostoSaudeContext } from './buscar-posto-saude.model';

@Component
export default class BuscarPostoSaudeDetailsComponent extends Vue {
  private buscarPostoSaudeService: BuscarPostoSaudeService = new BuscarPostoSaudeService();
  private taskContext: BuscarPostoSaudeContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.buscarPostoSaudeService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
