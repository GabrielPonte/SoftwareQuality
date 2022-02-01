import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Agendamento = () => import('@/entities/agendamento/agendamento.vue');
// prettier-ignore
const AgendamentoDetails = () => import('@/entities/agendamento/agendamento-details.vue');
// prettier-ignore
const AgendamentoProcessDetails = () => import('@/entities/agendamento-process/agendamento-process-details.vue');
// prettier-ignore
const AgendamentoProcessList = () => import('@/entities/agendamento-process/agendamento-process-list.vue');
// prettier-ignore
const AgendamentoStartFormInit = () => import('@/entities/agendamento-process/agendamento-start-form-init.vue');
// prettier-ignore
const AgendamentoProcess_AgendarTesteCovidDetails = () => import('@/entities/agendamento-process/agendar-teste-covid/agendar-teste-covid-details.vue');
// prettier-ignore
const AgendamentoProcess_AgendarTesteCovidExecute = () => import('@/entities/agendamento-process/agendar-teste-covid/agendar-teste-covid-execute.vue');
// prettier-ignore
const AgendamentoProcess_BuscarPostoSaudeDetails = () => import('@/entities/agendamento-process/buscar-posto-saude/buscar-posto-saude-details.vue');
// prettier-ignore
const AgendamentoProcess_BuscarPostoSaudeExecute = () => import('@/entities/agendamento-process/buscar-posto-saude/buscar-posto-saude-execute.vue');
// prettier-ignore
const AgendamentoProcess_PreencherDadosDetails = () => import('@/entities/agendamento-process/preencher-dados/preencher-dados-details.vue');
// prettier-ignore
const AgendamentoProcess_PreencherDadosExecute = () => import('@/entities/agendamento-process/preencher-dados/preencher-dados-execute.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/agendamento',
    name: 'Agendamento',
    component: Agendamento,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/agendamento/:agendamentoId/view',
    name: 'AgendamentoView',
    component: AgendamentoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/AgendamentoProcess/instance/:processInstanceId/view',
    name: 'AgendamentoProcessView',
    component: AgendamentoProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/AgendamentoProcess/instances',
    name: 'AgendamentoProcessList',
    component: AgendamentoProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/AgendamentoProcess/init',
    name: 'AgendamentoStartFormInit',
    component: AgendamentoStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/AgendamentoProcess/task/taskAgendar/:taskInstanceId/view',
    name: 'AgendamentoProcess_AgendarTesteCovidDetails',
    component: AgendamentoProcess_AgendarTesteCovidDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/AgendamentoProcess/task/taskAgendar/:taskInstanceId/execute',
    name: 'AgendamentoProcess_AgendarTesteCovidExecute',
    component: AgendamentoProcess_AgendarTesteCovidExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/AgendamentoProcess/task/taskBuscaPosto/:taskInstanceId/view',
    name: 'AgendamentoProcess_BuscarPostoSaudeDetails',
    component: AgendamentoProcess_BuscarPostoSaudeDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/AgendamentoProcess/task/taskBuscaPosto/:taskInstanceId/execute',
    name: 'AgendamentoProcess_BuscarPostoSaudeExecute',
    component: AgendamentoProcess_BuscarPostoSaudeExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/AgendamentoProcess/task/taskPreencherDados/:taskInstanceId/view',
    name: 'AgendamentoProcess_PreencherDadosDetails',
    component: AgendamentoProcess_PreencherDadosDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/AgendamentoProcess/task/taskPreencherDados/:taskInstanceId/execute',
    name: 'AgendamentoProcess_PreencherDadosExecute',
    component: AgendamentoProcess_PreencherDadosExecute,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
