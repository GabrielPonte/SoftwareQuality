<template>
  <div>
    <h2 class="jh-entity-heading" data-cy="agendamentoProcessDetailsHeading">
      <span v-text="$t('jhipsterApp.agendamentoProcess.home.title')">AgendamentoProcess</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('jhipsterApp.agendamentoProcess.home.refreshListLabel')">Refresh List</span>
        </button>

        <router-link :to="`/process-definition/AgendamentoProcess/init`" tag="button" class="btn btn-primary mr-2">
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span v-text="$t('jhipsterApp.agendamentoProcess.home.createLabel')"> Create a new Travel Plan Process Instance </span>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && agendamentoProcessList && agendamentoProcessList.length === 0">
      <span v-text="$t('jhipsterApp.agendamentoProcess.home.notFound')">No agendamentoProcess found</span>
    </div>
    <div class="table-responsive" v-if="agendamentoProcessList && agendamentoProcessList.length > 0">
      <table class="table table-striped" aria-describedby="agendamentoProcessList">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span>Process Instance</span></th>
            <th scope="row"><span>Agendamento</span></th>
            <th scope="row"><span>Status</span></th>
            <th scope="row"><span>Start Date</span></th>
            <th scope="row"><span>End Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="agendamentoProcess in agendamentoProcessList" :key="agendamentoProcess.id" data-cy="entityTable">
            <td>{{ agendamentoProcess.id }}</td>
            <td>
              <div v-if="agendamentoProcess.processInstance">
                <router-link :to="`/process-definition/AgendamentoProcess/instance/${agendamentoProcess.processInstance.id}/view`">
                  {{ agendamentoProcess.processInstance.businessKey }}
                </router-link>
              </div>
            </td>
            <td>
              <div v-if="agendamentoProcess.agendamento">
                <router-link :to="{ name: 'AgendamentoView', params: { agendamentoId: agendamentoProcess.agendamento.id } }">{{
                  agendamentoProcess.agendamento.id
                }}</router-link>
              </div>
            </td>
            <td>
              <akip-show-process-instance-status :status="agendamentoProcess.processInstance.status"></akip-show-process-instance-status>
            </td>
            <td>
              {{
                agendamentoProcess.processInstance.startDate ? $d(Date.parse(agendamentoProcess.processInstance.startDate), 'short') : ''
              }}
            </td>
            <td>
              {{ agendamentoProcess.processInstance.endDate ? $d(Date.parse(agendamentoProcess.processInstance.endDate), 'short') : '' }}
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="`/process-definition/AgendamentoProcess/instance/${agendamentoProcess.processInstance.id}/view`"
                  tag="button"
                  class="btn btn-info btn-sm details"
                  data-cy="entityDetailsButton"
                >
                  <font-awesome-icon icon="eye"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
      <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
    </button>
  </div>
</template>

<script lang="ts" src="./agendamento-process-list.component.ts"></script>
