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
                v-text="$t('softwareQualityApp.taskRecebaEmailC19.receberEmail')"
                for="task-receba-email-c-19-receberEmail"
                >Receber Email</label
              >
              <input
                type="text"
                class="form-control"
                name="receberEmail"
                id="task-receba-email-c-19-receberEmail"
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
                v-text="$t('softwareQualityApp.taskRecebaEmailC19.recebaEmail')"
                for="task-receba-email-c-19-recebaEmail"
                >Receba Email</label
              >
              <select
                class="form-control"
                id="task-receba-email-c-19-recebaEmail"
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

<script lang="ts" src="./task-receba-email-c-19-execute.component.ts"></script>
