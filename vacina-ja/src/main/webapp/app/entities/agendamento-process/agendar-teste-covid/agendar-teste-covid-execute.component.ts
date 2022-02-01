import { Component, Vue, Inject } from 'vue-property-decorator';

import AgendarTesteCovidService from './agendar-teste-covid.service';
import { AgendarTesteCovidContext } from './agendar-teste-covid.model';

const validations: any = {
  taskContext: {
    agendamentoProcess: {
      agendamento: {
        data: {},
        hora: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class AgendarTesteCovidExecuteComponent extends Vue {
  private agendarTesteCovidService: AgendarTesteCovidService = new AgendarTesteCovidService();
  private taskContext: AgendarTesteCovidContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.agendarTesteCovidService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.agendarTesteCovidService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
