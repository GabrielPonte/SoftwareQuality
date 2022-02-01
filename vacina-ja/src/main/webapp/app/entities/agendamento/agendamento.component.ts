import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IAgendamento } from '@/shared/model/agendamento.model';

import AgendamentoService from './agendamento.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Agendamento extends Vue {
  @Inject('agendamentoService') private agendamentoService: () => AgendamentoService;
  private removeId: number = null;

  public agendamentos: IAgendamento[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllAgendamentos();
  }

  public clear(): void {
    this.retrieveAllAgendamentos();
  }

  public retrieveAllAgendamentos(): void {
    this.isFetching = true;

    this.agendamentoService()
      .retrieve()
      .then(
        res => {
          this.agendamentos = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
