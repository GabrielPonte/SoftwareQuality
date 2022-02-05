import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITipoInformacao } from '@/shared/model/tipo-informacao.model';

import TipoInformacaoService from './tipo-informacao.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TipoInformacao extends Vue {
  @Inject('tipoInformacaoService') private tipoInformacaoService: () => TipoInformacaoService;
  private removeId: number = null;

  public tipoInformacaos: ITipoInformacao[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTipoInformacaos();
  }

  public clear(): void {
    this.retrieveAllTipoInformacaos();
  }

  public retrieveAllTipoInformacaos(): void {
    this.isFetching = true;

    this.tipoInformacaoService()
      .retrieve()
      .then(
        res => {
          this.tipoInformacaos = res.data;
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

  public prepareRemove(instance: ITipoInformacao): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTipoInformacao(): void {
    this.tipoInformacaoService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('softwareQualityApp.tipoInformacao.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTipoInformacaos();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
