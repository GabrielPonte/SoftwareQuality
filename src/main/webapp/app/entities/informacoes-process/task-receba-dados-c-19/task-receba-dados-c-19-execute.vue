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
                v-text="$t('softwareQualityApp.taskRecebaDadosC19.tipoInformacao')"
                for="task-receba-dados-c-19-tipoInformacao"
                >Tipo Informacao</label
              >
              <select
                class="form-control"
                id="task-receba-dados-c-19-tipoInformacao"
                data-cy="tipoInformacao"
                name="tipoInformacao"
                v-model="taskContext.informacoesProcess.informacoes.tipoInformacao"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.informacoesProcess.informacoes.tipoInformacao &&
                    tipoInformacaoOption.id === taskContext.informacoesProcess.informacoes.tipoInformacao.id
                      ? taskContext.informacoesProcess.informacoes.tipoInformacao
                      : tipoInformacaoOption
                  "
                  v-for="tipoInformacaoOption in tipoInformacaos"
                  :key="tipoInformacaoOption.id"
                >
                  {{ tipoInformacaoOption.tipoInfo }}
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

<script lang="ts" src="./task-receba-dados-c-19-execute.component.ts"></script>
