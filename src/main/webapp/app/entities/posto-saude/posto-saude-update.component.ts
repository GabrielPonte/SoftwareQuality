import { Component, Vue, Inject } from 'vue-property-decorator';

import { IPostoSaude, PostoSaude } from '@/shared/model/posto-saude.model';
import PostoSaudeService from './posto-saude.service';

const validations: any = {
  postoSaude: {
    nomePosto: {},
    estadoPosto: {},
    cidadePosto: {},
  },
};

@Component({
  validations,
})
export default class PostoSaudeUpdate extends Vue {
  @Inject('postoSaudeService') private postoSaudeService: () => PostoSaudeService;
  public postoSaude: IPostoSaude = new PostoSaude();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.postoSaudeId) {
        vm.retrievePostoSaude(to.params.postoSaudeId);
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
    if (this.postoSaude.id) {
      this.postoSaudeService()
        .update(this.postoSaude)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('softwareQualityApp.postoSaude.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.postoSaudeService()
        .create(this.postoSaude)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('softwareQualityApp.postoSaude.created', { param: param.id });
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

  public retrievePostoSaude(postoSaudeId): void {
    this.postoSaudeService()
      .find(postoSaudeId)
      .then(res => {
        this.postoSaude = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
