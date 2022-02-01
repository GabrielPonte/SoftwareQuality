import { Component, Vue, Inject } from 'vue-property-decorator';

import AgendarTesteCovidService from './agendar-teste-covid.service';
import { AgendarTesteCovidContext } from './agendar-teste-covid.model';

@Component
export default class AgendarTesteCovidDetailsComponent extends Vue {
  private agendarTesteCovidService: AgendarTesteCovidService = new AgendarTesteCovidService();
  private taskContext: AgendarTesteCovidContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.agendarTesteCovidService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
