import { Component, Vue, Inject } from 'vue-property-decorator';

import BuscarPostoSaudeService from './buscar-posto-saude.service';
import { BuscarPostoSaudeContext } from './buscar-posto-saude.model';

const validations: any = {
  taskContext: {
    agendamentoProcess: {
      agendamento: {
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
export default class BuscarPostoSaudeExecuteComponent extends Vue {
  private buscarPostoSaudeService: BuscarPostoSaudeService = new BuscarPostoSaudeService();
  private taskContext: BuscarPostoSaudeContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.buscarPostoSaudeService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.buscarPostoSaudeService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
