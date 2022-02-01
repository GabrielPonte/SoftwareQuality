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
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskAgendar.data')" for="task-agendar-data">Data</label>
              <b-input-group class="mb-3">
                <b-input-group-prepend>
                  <b-form-datepicker
                    aria-controls="task-agendar-data"
                    v-model="$v.taskContext.informacoesProcess.informacoes.data.$model"
                    name="data"
                    class="form-control"
                    :locale="currentLanguage"
                    button-only
                    today-button
                    reset-button
                    close-button
                  >
                  </b-form-datepicker>
                </b-input-group-prepend>

                <b-form-input
                  id="task-agendar-data"
                  data-cy="data"
                  type="text"
                  class="form-control"
                  name="data"
                  :class="{
                    valid: !$v.taskContext.informacoesProcess.informacoes.data.$invalid,
                    invalid: $v.taskContext.informacoesProcess.informacoes.data.$invalid,
                  }"
                  v-model="$v.taskContext.informacoesProcess.informacoes.data.$model"
                />
              </b-input-group>
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('softwareQualityApp.taskAgendar.hora')" for="task-agendar-hora">Hora</label>
              <input
                type="text"
                class="form-control"
                name="hora"
                id="task-agendar-hora"
                data-cy="hora"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.hora.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.hora.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.hora.$model"
              />
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

<script lang="ts" src="./task-agendar-execute.component.ts"></script>
