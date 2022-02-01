import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAgendamentoProcess } from '@/shared/model/agendamento-process.model';
import AgendamentoProcessService from './agendamento-process.service';

@Component
export default class AgendamentoProcessDetailsComponent extends Vue {
  @Inject('agendamentoProcessService') private agendamentoProcessService: () => AgendamentoProcessService;
  public agendamentoProcess: IAgendamentoProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveAgendamentoProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveAgendamentoProcess(agendamentoProcessId) {
    this.isFetching = true;
    this.agendamentoProcessService()
      .find(agendamentoProcessId)
      .then(
        res => {
          this.agendamentoProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
