import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILocalDesejado, LocalDesejado } from '@/shared/model/local-desejado.model';
import LocalDesejadoService from './local-desejado.service';

const validations: any = {
  localDesejado: {
    localCovid: {},
  },
};

@Component({
  validations,
})
export default class LocalDesejadoUpdate extends Vue {
  @Inject('localDesejadoService') private localDesejadoService: () => LocalDesejadoService;
  public localDesejado: ILocalDesejado = new LocalDesejado();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.localDesejadoId) {
        vm.retrieveLocalDesejado(to.params.localDesejadoId);
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
    if (this.localDesejado.id) {
      this.localDesejadoService()
        .update(this.localDesejado)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('softwareQualityApp.localDesejado.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.localDesejadoService()
        .create(this.localDesejado)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('softwareQualityApp.localDesejado.created', { param: param.id });
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

  public retrieveLocalDesejado(localDesejadoId): void {
    this.localDesejadoService()
      .find(localDesejadoId)
      .then(res => {
        this.localDesejado = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
