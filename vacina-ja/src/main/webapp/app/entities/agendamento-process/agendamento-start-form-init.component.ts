import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAgendamentoProcess, AgendamentoProcess } from '@/shared/model/agendamento-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { Agendamento } from '@/shared/model/agendamento.model';
import AgendamentoProcessService from './agendamento-process.service';

const validations: any = {
  agendamentoProcess: {
    agendamento: {
      nomeCompleto: {},
      CPF: {},
      idade: {},
      qtdVacinas: {},
      sintomas: {},
    },
  },
};

@Component({
  validations,
})
export default class AgendamentoStartFormInitComponent extends Vue {
  @Inject('agendamentoProcessService') private agendamentoProcessService: () => AgendamentoProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'AgendamentoProcess';
  public agendamentoProcess: IAgendamentoProcess = new AgendamentoProcess();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initAgendamentoStartForm();
      vm.initRelationships();
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

    this.agendamentoProcessService()
      .create(this.agendamentoProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('jhipsterApp.agendamentoStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initAgendamentoStartForm(): void {
    this.agendamentoProcess.agendamento = new Agendamento();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.agendamentoProcess.processInstance = new ProcessInstance();
      this.agendamentoProcess.processInstance.processDefinition = res;
    });
  }
}
