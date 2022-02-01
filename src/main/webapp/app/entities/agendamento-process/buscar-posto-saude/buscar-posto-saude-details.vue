<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('jhipsterApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.buscarPostoSaude.cidade')">cidade</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="cidade"
                id="agendamento-cidade"
                data-cy="cidade"
                v-model="taskContext.agendamentoProcess.agendamento.cidade"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.buscarPostoSaude.estado')">estado</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="estado"
                id="agendamento-estado"
                data-cy="estado"
                v-model="taskContext.agendamentoProcess.agendamento.estado"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.buscarPostoSaude.nomePosto')">nomePosto</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="nomePosto"
                id="agendamento-nomePosto"
                data-cy="nomePosto"
                v-model="taskContext.agendamentoProcess.agendamento.nomePosto"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/AgendamentoProcess/task/taskBuscaPosto/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./buscar-posto-saude-details.component.ts"></script>
