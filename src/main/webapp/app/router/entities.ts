import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Informacoes = () => import('@/entities/informacoes/informacoes.vue');
// prettier-ignore
const InformacoesDetails = () => import('@/entities/informacoes/informacoes-details.vue');
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
const LocalDesejado = () => import('@/entities/local-desejado/local-desejado.vue');
// prettier-ignore
const LocalDesejadoUpdate = () => import('@/entities/local-desejado/local-desejado-update.vue');
// prettier-ignore
const LocalDesejadoDetails = () => import('@/entities/local-desejado/local-desejado-details.vue');
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
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
