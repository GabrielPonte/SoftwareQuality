import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILocalDesejado } from '@/shared/model/local-desejado.model';

import LocalDesejadoService from './local-desejado.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class LocalDesejado extends Vue {
  @Inject('localDesejadoService') private localDesejadoService: () => LocalDesejadoService;
  private removeId: number = null;

  public localDesejados: ILocalDesejado[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllLocalDesejados();
  }

  public clear(): void {
    this.retrieveAllLocalDesejados();
  }

  public retrieveAllLocalDesejados(): void {
    this.isFetching = true;

    this.localDesejadoService()
      .retrieve()
      .then(
        res => {
          this.localDesejados = res.data;
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

  public prepareRemove(instance: ILocalDesejado): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeLocalDesejado(): void {
    this.localDesejadoService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('softwareQualityApp.localDesejado.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllLocalDesejados();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
