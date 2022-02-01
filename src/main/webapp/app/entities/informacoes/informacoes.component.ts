import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IInformacoes } from '@/shared/model/informacoes.model';

import InformacoesService from './informacoes.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Informacoes extends Vue {
  @Inject('informacoesService') private informacoesService: () => InformacoesService;
  private removeId: number = null;

  public informacoes: IInformacoes[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllInformacoess();
  }

  public clear(): void {
    this.retrieveAllInformacoess();
  }

  public retrieveAllInformacoess(): void {
    this.isFetching = true;

    this.informacoesService()
      .retrieve()
      .then(
        res => {
          this.informacoes = res.data;
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
