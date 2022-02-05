import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRecebaEmail } from '@/shared/model/receba-email.model';
import RecebaEmailService from './receba-email.service';

@Component
export default class RecebaEmailDetails extends Vue {
  @Inject('recebaEmailService') private recebaEmailService: () => RecebaEmailService;
  public recebaEmail: IRecebaEmail = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.recebaEmailId) {
        vm.retrieveRecebaEmail(to.params.recebaEmailId);
      }
    });
  }

  public retrieveRecebaEmail(recebaEmailId) {
    this.recebaEmailService()
      .find(recebaEmailId)
      .then(res => {
        this.recebaEmail = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
