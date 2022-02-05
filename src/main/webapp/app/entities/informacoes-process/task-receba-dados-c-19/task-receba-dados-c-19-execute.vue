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
                v-text="$t('softwareQualityApp.taskRecebaDadosC19.tipoDeInformacao')"
                for="task-receba-dados-c-19-tipoDeInformacao"
                >Tipo De Informacao</label
              >
              <input
                type="text"
                class="form-control"
                name="tipoDeInformacao"
                id="task-receba-dados-c-19-tipoDeInformacao"
                data-cy="tipoDeInformacao"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.tipoDeInformacao.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.tipoDeInformacao.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.tipoDeInformacao.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('softwareQualityApp.taskRecebaDadosC19.local')"
                for="task-receba-dados-c-19-local"
                >Local</label
              >
              <input
                type="text"
                class="form-control"
                name="local"
                id="task-receba-dados-c-19-local"
                data-cy="local"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.local.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.local.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.local.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('softwareQualityApp.taskRecebaDadosC19.receberEmail')"
                for="task-receba-dados-c-19-receberEmail"
                >Receber Email</label
              >
              <input
                type="text"
                class="form-control"
                name="receberEmail"
                id="task-receba-dados-c-19-receberEmail"
                data-cy="receberEmail"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.receberEmail.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.receberEmail.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.receberEmail.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('softwareQualityApp.taskRecebaDadosC19.localDesejado')"
                for="task-receba-dados-c-19-localDesejado"
                >Local Desejado</label
              >
              <select
                class="form-control"
                id="task-receba-dados-c-19-localDesejado"
                data-cy="localDesejado"
                name="localDesejado"
                v-model="taskContext.informacoesProcess.informacoes.localDesejado"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.informacoesProcess.informacoes.localDesejado &&
                    localDesejadoOption.id === taskContext.informacoesProcess.informacoes.localDesejado.id
                      ? taskContext.informacoesProcess.informacoes.localDesejado
                      : localDesejadoOption
                  "
                  v-for="localDesejadoOption in localDesejados"
                  :key="localDesejadoOption.id"
                >
                  {{ localDesejadoOption.localCovid }}
                </option>
              </select>
            </div>
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
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('softwareQualityApp.taskRecebaDadosC19.recebaEmail')"
                for="task-receba-dados-c-19-recebaEmail"
                >Receba Email</label
              >
              <select
                class="form-control"
                id="task-receba-dados-c-19-recebaEmail"
                data-cy="recebaEmail"
                name="recebaEmail"
                v-model="taskContext.informacoesProcess.informacoes.recebaEmail"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.informacoesProcess.informacoes.recebaEmail &&
                    recebaEmailOption.id === taskContext.informacoesProcess.informacoes.recebaEmail.id
                      ? taskContext.informacoesProcess.informacoes.recebaEmail
                      : recebaEmailOption
                  "
                  v-for="recebaEmailOption in recebaEmails"
                  :key="recebaEmailOption.id"
                >
                  {{ recebaEmailOption.opcaoEmail }}
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
