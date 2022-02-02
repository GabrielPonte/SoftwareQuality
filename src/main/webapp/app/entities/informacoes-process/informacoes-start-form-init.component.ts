import { Component, Vue, Inject } from 'vue-property-decorator';

import { IInformacoesProcess, InformacoesProcess } from '@/shared/model/informacoes-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { Informacoes } from '@/shared/model/informacoes.model';
import InformacoesProcessService from './informacoes-process.service';

const validations: any = {
  informacoesProcess: {
    informacoes: {
      opcao: {},
      nomeCompleto: {},
      email: {},
      idade: {},
    },
  },
};

@Component({
  validations,
})
export default class InformacoesStartFormInitComponent extends Vue {
  @Inject('informacoesProcessService') private informacoesProcessService: () => InformacoesProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'InformacoesProcess';
  public informacoesProcess: IInformacoesProcess = new InformacoesProcess();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initInformacoesStartForm();
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

    this.informacoesProcessService()
      .create(this.informacoesProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('softwareQualityApp.informacoesStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initInformacoesStartForm(): void {
    this.informacoesProcess.informacoes = new Informacoes();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.informacoesProcess.processInstance = new ProcessInstance();
      this.informacoesProcess.processInstance.processDefinition = res;
    });
  }
}
