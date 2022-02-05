import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Informacoes = () => import('@/entities/informacoes/informacoes.vue');
// prettier-ignore
const InformacoesDetails = () => import('@/entities/informacoes/informacoes-details.vue');
// prettier-ignore
const LocalDesejado = () => import('@/entities/local-desejado/local-desejado.vue');
// prettier-ignore
const LocalDesejadoUpdate = () => import('@/entities/local-desejado/local-desejado-update.vue');
// prettier-ignore
const LocalDesejadoDetails = () => import('@/entities/local-desejado/local-desejado-details.vue');
// prettier-ignore
const OpcaoEscolhida = () => import('@/entities/opcao-escolhida/opcao-escolhida.vue');
// prettier-ignore
const OpcaoEscolhidaUpdate = () => import('@/entities/opcao-escolhida/opcao-escolhida-update.vue');
// prettier-ignore
const OpcaoEscolhidaDetails = () => import('@/entities/opcao-escolhida/opcao-escolhida-details.vue');
// prettier-ignore
const PostoSaude = () => import('@/entities/posto-saude/posto-saude.vue');
// prettier-ignore
const PostoSaudeUpdate = () => import('@/entities/posto-saude/posto-saude-update.vue');
// prettier-ignore
const PostoSaudeDetails = () => import('@/entities/posto-saude/posto-saude-details.vue');
// prettier-ignore
const RecebaEmail = () => import('@/entities/receba-email/receba-email.vue');
// prettier-ignore
const RecebaEmailUpdate = () => import('@/entities/receba-email/receba-email-update.vue');
// prettier-ignore
const RecebaEmailDetails = () => import('@/entities/receba-email/receba-email-details.vue');
// prettier-ignore
const TipoInformacao = () => import('@/entities/tipo-informacao/tipo-informacao.vue');
// prettier-ignore
const TipoInformacaoUpdate = () => import('@/entities/tipo-informacao/tipo-informacao-update.vue');
// prettier-ignore
const TipoInformacaoDetails = () => import('@/entities/tipo-informacao/tipo-informacao-details.vue');
// prettier-ignore
const InformacoesProcessDetails = () => import('@/entities/informacoes-process/informacoes-process-details.vue');
// prettier-ignore
const InformacoesProcessList = () => import('@/entities/informacoes-process/informacoes-process-list.vue');
// prettier-ignore
const InformacoesStartFormInit = () => import('@/entities/informacoes-process/informacoes-start-form-init.vue');
// prettier-ignore
const InformacoesProcess_TaskAgendarDetails = () => import('@/entities/informacoes-process/task-agendar/task-agendar-details.vue');
// prettier-ignore
const InformacoesProcess_TaskAgendarExecute = () => import('@/entities/informacoes-process/task-agendar/task-agendar-execute.vue');
// prettier-ignore
const InformacoesProcess_TaskBuscaPostoDetails = () => import('@/entities/informacoes-process/task-busca-posto/task-busca-posto-details.vue');
// prettier-ignore
const InformacoesProcess_TaskBuscaPostoExecute = () => import('@/entities/informacoes-process/task-busca-posto/task-busca-posto-execute.vue');
// prettier-ignore
const InformacoesProcess_TaskPreencherDadosDetails = () => import('@/entities/informacoes-process/task-preencher-dados/task-preencher-dados-details.vue');
// prettier-ignore
const InformacoesProcess_TaskPreencherDadosExecute = () => import('@/entities/informacoes-process/task-preencher-dados/task-preencher-dados-execute.vue');
// prettier-ignore
const InformacoesProcess_TaskRecebaDadosC19Details = () => import('@/entities/informacoes-process/task-receba-dados-c-19/task-receba-dados-c-19-details.vue');
// prettier-ignore
const InformacoesProcess_TaskRecebaDadosC19Execute = () => import('@/entities/informacoes-process/task-receba-dados-c-19/task-receba-dados-c-19-execute.vue');
// prettier-ignore
const InformacoesProcess_TaskRecebaEmailC19Details = () => import('@/entities/informacoes-process/task-receba-email-c-19/task-receba-email-c-19-details.vue');
// prettier-ignore
const InformacoesProcess_TaskRecebaEmailC19Execute = () => import('@/entities/informacoes-process/task-receba-email-c-19/task-receba-email-c-19-execute.vue');
// prettier-ignore
const InformacoesProcess_TaskRecebaLocalC19Details = () => import('@/entities/informacoes-process/task-receba-local-c-19/task-receba-local-c-19-details.vue');
// prettier-ignore
const InformacoesProcess_TaskRecebaLocalC19Execute = () => import('@/entities/informacoes-process/task-receba-local-c-19/task-receba-local-c-19-execute.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/informacoes',
    name: 'Informacoes',
    component: Informacoes,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/informacoes/:informacoesId/view',
    name: 'InformacoesView',
    component: InformacoesDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/local-desejado',
    name: 'LocalDesejado',
    component: LocalDesejado,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/local-desejado/new',
    name: 'LocalDesejadoCreate',
    component: LocalDesejadoUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/local-desejado/:localDesejadoId/edit',
    name: 'LocalDesejadoEdit',
    component: LocalDesejadoUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/local-desejado/:localDesejadoId/view',
    name: 'LocalDesejadoView',
    component: LocalDesejadoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/opcao-escolhida',
    name: 'OpcaoEscolhida',
    component: OpcaoEscolhida,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/opcao-escolhida/new',
    name: 'OpcaoEscolhidaCreate',
    component: OpcaoEscolhidaUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/opcao-escolhida/:opcaoEscolhidaId/edit',
    name: 'OpcaoEscolhidaEdit',
    component: OpcaoEscolhidaUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/opcao-escolhida/:opcaoEscolhidaId/view',
    name: 'OpcaoEscolhidaView',
    component: OpcaoEscolhidaDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/posto-saude',
    name: 'PostoSaude',
    component: PostoSaude,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/posto-saude/new',
    name: 'PostoSaudeCreate',
    component: PostoSaudeUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/posto-saude/:postoSaudeId/edit',
    name: 'PostoSaudeEdit',
    component: PostoSaudeUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/posto-saude/:postoSaudeId/view',
    name: 'PostoSaudeView',
    component: PostoSaudeDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/receba-email',
    name: 'RecebaEmail',
    component: RecebaEmail,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/receba-email/new',
    name: 'RecebaEmailCreate',
    component: RecebaEmailUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/receba-email/:recebaEmailId/edit',
    name: 'RecebaEmailEdit',
    component: RecebaEmailUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/receba-email/:recebaEmailId/view',
    name: 'RecebaEmailView',
    component: RecebaEmailDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tipo-informacao',
    name: 'TipoInformacao',
    component: TipoInformacao,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tipo-informacao/new',
    name: 'TipoInformacaoCreate',
    component: TipoInformacaoUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tipo-informacao/:tipoInformacaoId/edit',
    name: 'TipoInformacaoEdit',
    component: TipoInformacaoUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tipo-informacao/:tipoInformacaoId/view',
    name: 'TipoInformacaoView',
    component: TipoInformacaoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/instance/:processInstanceId/view',
    name: 'InformacoesProcessView',
    component: InformacoesProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/instances',
    name: 'InformacoesProcessList',
    component: InformacoesProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/init',
    name: 'InformacoesStartFormInit',
    component: InformacoesStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/task/taskAgendar/:taskInstanceId/view',
    name: 'InformacoesProcess_TaskAgendarDetails',
    component: InformacoesProcess_TaskAgendarDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/task/taskAgendar/:taskInstanceId/execute',
    name: 'InformacoesProcess_TaskAgendarExecute',
    component: InformacoesProcess_TaskAgendarExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/task/taskBuscaPosto/:taskInstanceId/view',
    name: 'InformacoesProcess_TaskBuscaPostoDetails',
    component: InformacoesProcess_TaskBuscaPostoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/task/taskBuscaPosto/:taskInstanceId/execute',
    name: 'InformacoesProcess_TaskBuscaPostoExecute',
    component: InformacoesProcess_TaskBuscaPostoExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/task/taskPreencherDados/:taskInstanceId/view',
    name: 'InformacoesProcess_TaskPreencherDadosDetails',
    component: InformacoesProcess_TaskPreencherDadosDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/task/taskPreencherDados/:taskInstanceId/execute',
    name: 'InformacoesProcess_TaskPreencherDadosExecute',
    component: InformacoesProcess_TaskPreencherDadosExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/task/taskRecebaDadosC19/:taskInstanceId/view',
    name: 'InformacoesProcess_TaskRecebaDadosC19Details',
    component: InformacoesProcess_TaskRecebaDadosC19Details,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/task/taskRecebaDadosC19/:taskInstanceId/execute',
    name: 'InformacoesProcess_TaskRecebaDadosC19Execute',
    component: InformacoesProcess_TaskRecebaDadosC19Execute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/task/taskRecebaEmailC19/:taskInstanceId/view',
    name: 'InformacoesProcess_TaskRecebaEmailC19Details',
    component: InformacoesProcess_TaskRecebaEmailC19Details,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/task/taskRecebaEmailC19/:taskInstanceId/execute',
    name: 'InformacoesProcess_TaskRecebaEmailC19Execute',
    component: InformacoesProcess_TaskRecebaEmailC19Execute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/task/taskRecebaLocalC19/:taskInstanceId/view',
    name: 'InformacoesProcess_TaskRecebaLocalC19Details',
    component: InformacoesProcess_TaskRecebaLocalC19Details,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/InformacoesProcess/task/taskRecebaLocalC19/:taskInstanceId/execute',
    name: 'InformacoesProcess_TaskRecebaLocalC19Execute',
    component: InformacoesProcess_TaskRecebaLocalC19Execute,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
