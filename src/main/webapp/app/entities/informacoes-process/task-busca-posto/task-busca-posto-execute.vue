<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('softwareQualityApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('softwareQualityApp.taskBuscaPosto.postoSaude')"
                for="task-busca-posto-postoSaude"
                >Posto Saude</label
              >
              <select
                class="form-control"
                id="task-busca-posto-postoSaude"
                data-cy="postoSaude"
                name="postoSaude"
                v-model="taskContext.informacoesProcess.informacoes.postoSaude"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.informacoesProcess.informacoes.postoSaude &&
                    postoSaudeOption.id === taskContext.informacoesProcess.informacoes.postoSaude.id
                      ? taskContext.informacoesProcess.informacoes.postoSaude
                      : postoSaudeOption
                  "
                  v-for="postoSaudeOption in postoSaudes"
                  :key="postoSaudeOption.id"
                >
                  {{ postoSaudeOption.nomePosto }}
                </option>
              </select>
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-busca-posto-execute.component.ts"></script>
