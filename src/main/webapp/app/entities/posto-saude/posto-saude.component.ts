import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IPostoSaude } from '@/shared/model/posto-saude.model';

import PostoSaudeService from './posto-saude.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class PostoSaude extends Vue {
  @Inject('postoSaudeService') private postoSaudeService: () => PostoSaudeService;
  private removeId: number = null;

  public postoSaudes: IPostoSaude[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllPostoSaudes();
  }

  public clear(): void {
    this.retrieveAllPostoSaudes();
  }

  public retrieveAllPostoSaudes(): void {
    this.isFetching = true;

    this.postoSaudeService()
      .retrieve()
      .then(
        res => {
          this.postoSaudes = res.data;
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

  public prepareRemove(instance: IPostoSaude): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removePostoSaude(): void {
    this.postoSaudeService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('softwareQualityApp.postoSaude.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllPostoSaudes();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
