import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITipoInformacao } from '@/shared/model/tipo-informacao.model';
import TipoInformacaoService from './tipo-informacao.service';

@Component
export default class TipoInformacaoDetails extends Vue {
  @Inject('tipoInformacaoService') private tipoInformacaoService: () => TipoInformacaoService;
  public tipoInformacao: ITipoInformacao = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tipoInformacaoId) {
        vm.retrieveTipoInformacao(to.params.tipoInformacaoId);
      }
    });
  }

  public retrieveTipoInformacao(tipoInformacaoId) {
    this.tipoInformacaoService()
      .find(tipoInformacaoId)
      .then(res => {
        this.tipoInformacao = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
