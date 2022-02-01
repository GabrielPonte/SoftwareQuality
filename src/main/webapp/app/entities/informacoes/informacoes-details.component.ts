import { Component, Vue, Inject } from 'vue-property-decorator';

import { IInformacoes } from '@/shared/model/informacoes.model';
import InformacoesService from './informacoes.service';

@Component
export default class InformacoesDetails extends Vue {
  @Inject('informacoesService') private informacoesService: () => InformacoesService;
  public informacoes: IInformacoes = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.informacoesId) {
        vm.retrieveInformacoes(to.params.informacoesId);
      }
    });
  }

  public retrieveInformacoes(informacoesId) {
    this.informacoesService()
      .find(informacoesId)
      .then(res => {
        this.informacoes = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
