import { Component, Vue, Inject } from 'vue-property-decorator';

import RecebaEmailService from '@/entities/receba-email/receba-email.service';
import { IRecebaEmail } from '@/shared/model/receba-email.model';

import TaskRecebaEmailC19Service from './task-receba-email-c-19.service';
import { TaskRecebaEmailC19Context } from './task-receba-email-c-19.model';

const validations: any = {
  taskContext: {
    informacoesProcess: {
      informacoes: {
        receberEmail: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskRecebaEmailC19ExecuteComponent extends Vue {
  private taskRecebaEmailC19Service: TaskRecebaEmailC19Service = new TaskRecebaEmailC19Service();
  private taskContext: TaskRecebaEmailC19Context = {};

  @Inject('recebaEmailService') private recebaEmailService: () => RecebaEmailService;

  public recebaEmails: IRecebaEmail[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskRecebaEmailC19Service.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskRecebaEmailC19Service.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.recebaEmailService()
      .retrieve()
      .then(res => {
        this.recebaEmails = res.data;
      });
  }
}
