import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAgendamento } from '@/shared/model/agendamento.model';
import AgendamentoService from './agendamento.service';

@Component
export default class AgendamentoDetails extends Vue {
  @Inject('agendamentoService') private agendamentoService: () => AgendamentoService;
  public agendamento: IAgendamento = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.agendamentoId) {
        vm.retrieveAgendamento(to.params.agendamentoId);
      }
    });
  }

  public retrieveAgendamento(agendamentoId) {
    this.agendamentoService()
      .find(agendamentoId)
      .then(res => {
        this.agendamento = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
