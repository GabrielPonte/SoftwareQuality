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
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskBuscaPosto.nomeDoPosto')">nomeDoPosto</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="nomeDoPosto"
                id="informacoes-nomeDoPosto"
                data-cy="nomeDoPosto"
                v-model="taskContext.informacoesProcess.informacoes.nomeDoPosto"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskBuscaPosto.estadoDoPosto')">estadoDoPosto</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="estadoDoPosto"
                id="informacoes-estadoDoPosto"
                data-cy="estadoDoPosto"
                v-model="taskContext.informacoesProcess.informacoes.estadoDoPosto"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskBuscaPosto.cidadeDoPosto')">cidadeDoPosto</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="cidadeDoPosto"
                id="informacoes-cidadeDoPosto"
                data-cy="cidadeDoPosto"
                v-model="taskContext.informacoesProcess.informacoes.cidadeDoPosto"
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
          :to="`/process-definition/InformacoesProcess/task/taskBuscaPosto/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./task-busca-posto-details.component.ts"></script>
