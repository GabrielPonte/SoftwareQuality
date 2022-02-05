import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITipoInformacao, TipoInformacao } from '@/shared/model/tipo-informacao.model';
import TipoInformacaoService from './tipo-informacao.service';

const validations: any = {
  tipoInformacao: {
    tipoInfo: {},
  },
};

@Component({
  validations,
})
export default class TipoInformacaoUpdate extends Vue {
  @Inject('tipoInformacaoService') private tipoInformacaoService: () => TipoInformacaoService;
  public tipoInformacao: ITipoInformacao = new TipoInformacao();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tipoInformacaoId) {
        vm.retrieveTipoInformacao(to.params.tipoInformacaoId);
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
    if (this.tipoInformacao.id) {
      this.tipoInformacaoService()
        .update(this.tipoInformacao)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('softwareQualityApp.tipoInformacao.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.tipoInformacaoService()
        .create(this.tipoInformacao)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('softwareQualityApp.tipoInformacao.created', { param: param.id });
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

  public retrieveTipoInformacao(tipoInformacaoId): void {
    this.tipoInformacaoService()
      .find(tipoInformacaoId)
      .then(res => {
        this.tipoInformacao = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
