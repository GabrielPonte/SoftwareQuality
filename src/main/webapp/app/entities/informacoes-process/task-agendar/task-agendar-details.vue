<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('softwareQualityApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskAgendar.data')">data</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="data"
                id="informacoes-data"
                data-cy="data"
                v-model="taskContext.informacoesProcess.informacoes.data"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskAgendar.hora')">hora</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="hora"
                id="informacoes-hora"
                data-cy="hora"
                v-model="taskContext.informacoesProcess.informacoes.hora"
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
          :to="`/process-definition/InformacoesProcess/task/taskAgendar/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./task-agendar-details.component.ts"></script>
