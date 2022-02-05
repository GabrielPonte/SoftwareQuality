import { Component, Vue, Inject } from 'vue-property-decorator';

import { IOpcaoEscolhida, OpcaoEscolhida } from '@/shared/model/opcao-escolhida.model';
import OpcaoEscolhidaService from './opcao-escolhida.service';

const validations: any = {
  opcaoEscolhida: {
    opcaoInicial: {},
  },
};

@Component({
  validations,
})
export default class OpcaoEscolhidaUpdate extends Vue {
  @Inject('opcaoEscolhidaService') private opcaoEscolhidaService: () => OpcaoEscolhidaService;
  public opcaoEscolhida: IOpcaoEscolhida = new OpcaoEscolhida();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.opcaoEscolhidaId) {
        vm.retrieveOpcaoEscolhida(to.params.opcaoEscolhidaId);
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
    if (this.opcaoEscolhida.id) {
      this.opcaoEscolhidaService()
        .update(this.opcaoEscolhida)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('softwareQualityApp.opcaoEscolhida.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.opcaoEscolhidaService()
        .create(this.opcaoEscolhida)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('softwareQualityApp.opcaoEscolhida.created', { param: param.id });
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

  public retrieveOpcaoEscolhida(opcaoEscolhidaId): void {
    this.opcaoEscolhidaService()
      .find(opcaoEscolhidaId)
      .then(res => {
        this.opcaoEscolhida = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
