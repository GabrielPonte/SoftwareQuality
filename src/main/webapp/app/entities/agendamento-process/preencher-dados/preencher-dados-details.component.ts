import { Component, Vue, Inject } from 'vue-property-decorator';

import PreencherDadosService from './preencher-dados.service';
import { PreencherDadosContext } from './preencher-dados.model';

@Component
export default class PreencherDadosDetailsComponent extends Vue {
  private preencherDadosService: PreencherDadosService = new PreencherDadosService();
  private taskContext: PreencherDadosContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.preencherDadosService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
