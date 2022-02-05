import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRecebaEmail, RecebaEmail } from '@/shared/model/receba-email.model';
import RecebaEmailService from './receba-email.service';

const validations: any = {
  recebaEmail: {
    opcaoEmail: {},
  },
};

@Component({
  validations,
})
export default class RecebaEmailUpdate extends Vue {
  @Inject('recebaEmailService') private recebaEmailService: () => RecebaEmailService;
  public recebaEmail: IRecebaEmail = new RecebaEmail();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.recebaEmailId) {
        vm.retrieveRecebaEmail(to.params.recebaEmailId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.recebaEmail.id) {
      this.recebaEmailService()
        .update(this.recebaEmail)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('softwareQualityApp.recebaEmail.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.recebaEmailService()
        .create(this.recebaEmail)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('softwareQualityApp.recebaEmail.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveRecebaEmail(recebaEmailId): void {
    this.recebaEmailService()
      .find(recebaEmailId)
      .then(res => {
        this.recebaEmail = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
