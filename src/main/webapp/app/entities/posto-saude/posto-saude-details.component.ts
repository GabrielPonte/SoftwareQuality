import { Component, Vue, Inject } from 'vue-property-decorator';

import { IPostoSaude } from '@/shared/model/posto-saude.model';
import PostoSaudeService from './posto-saude.service';

@Component
export default class PostoSaudeDetails extends Vue {
  @Inject('postoSaudeService') private postoSaudeService: () => PostoSaudeService;
  public postoSaude: IPostoSaude = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.postoSaudeId) {
        vm.retrievePostoSaude(to.params.postoSaudeId);
      }
    });
  }

  public retrievePostoSaude(postoSaudeId) {
    this.postoSaudeService()
      .find(postoSaudeId)
      .then(res => {
        this.postoSaude = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
