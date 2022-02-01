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
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.nomeCompleto')">nomeCompleto</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="nomeCompleto"
                id="agendamento-nomeCompleto"
                data-cy="nomeCompleto"
                v-model="taskContext.agendamentoProcess.agendamento.nomeCompleto"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.idade')">idade</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="idade"
                id="agendamento-idade"
                data-cy="idade"
                v-model="taskContext.agendamentoProcess.agendamento.idade"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.CPF')">CPF</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="CPF"
                id="agendamento-CPF"
                data-cy="CPF"
                v-model="taskContext.agendamentoProcess.agendamento.CPF"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.CEP')">CEP</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="CEP"
                id="agendamento-CEP"
                data-cy="CEP"
                v-model="taskContext.agendamentoProcess.agendamento.CEP"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.endereco')">endereco</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="endereco"
                id="agendamento-endereco"
                data-cy="endereco"
                v-model="taskContext.agendamentoProcess.agendamento.endereco"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.complemento')">complemento</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="complemento"
                id="agendamento-complemento"
                data-cy="complemento"
                v-model="taskContext.agendamentoProcess.agendamento.complemento"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.qtdVacinas')">qtdVacinas</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="qtdVacinas"
                id="agendamento-qtdVacinas"
                data-cy="qtdVacinas"
                v-model="taskContext.agendamentoProcess.agendamento.qtdVacinas"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.sintomas')">sintomas</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="sintomas"
                id="agendamento-sintomas"
                data-cy="sintomas"
                v-model="taskContext.agendamentoProcess.agendamento.sintomas"
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
          :to="`/process-definition/AgendamentoProcess/task/taskPreencherDados/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./preencher-dados-details.component.ts"></script>
