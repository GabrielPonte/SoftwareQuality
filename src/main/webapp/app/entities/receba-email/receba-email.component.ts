import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IRecebaEmail } from '@/shared/model/receba-email.model';

import RecebaEmailService from './receba-email.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class RecebaEmail extends Vue {
  @Inject('recebaEmailService') private recebaEmailService: () => RecebaEmailService;
  private removeId: number = null;

  public recebaEmails: IRecebaEmail[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllRecebaEmails();
  }

  public clear(): void {
    this.retrieveAllRecebaEmails();
  }

  public retrieveAllRecebaEmails(): void {
    this.isFetching = true;

    this.recebaEmailService()
      .retrieve()
      .then(
        res => {
          this.recebaEmails = res.data;
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

  public prepareRemove(instance: IRecebaEmail): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeRecebaEmail(): void {
    this.recebaEmailService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('softwareQualityApp.recebaEmail.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllRecebaEmails();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
