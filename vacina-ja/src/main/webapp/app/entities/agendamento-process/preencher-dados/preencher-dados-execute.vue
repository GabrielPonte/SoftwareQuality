<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('jhipsterApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.nomeCompleto')" for="preencher-dados-nomeCompleto"
                >Nome Completo</label
              >
              <input
                type="text"
                class="form-control"
                name="nomeCompleto"
                id="preencher-dados-nomeCompleto"
                data-cy="nomeCompleto"
                :class="{
                  valid: !$v.taskContext.agendamentoProcess.agendamento.nomeCompleto.$invalid,
                  invalid: $v.taskContext.agendamentoProcess.agendamento.nomeCompleto.$invalid,
                }"
                v-model="$v.taskContext.agendamentoProcess.agendamento.nomeCompleto.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.idade')" for="preencher-dados-idade">Idade</label>
              <input
                type="number"
                class="form-control"
                name="idade"
                id="preencher-dados-idade"
                data-cy="idade"
                :class="{
                  valid: !$v.taskContext.agendamentoProcess.agendamento.idade.$invalid,
                  invalid: $v.taskContext.agendamentoProcess.agendamento.idade.$invalid,
                }"
                v-model.number="$v.taskContext.agendamentoProcess.agendamento.idade.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.CPF')" for="preencher-dados-CPF">CPF</label>
              <input
                type="text"
                class="form-control"
                name="CPF"
                id="preencher-dados-CPF"
                data-cy="CPF"
                :class="{
                  valid: !$v.taskContext.agendamentoProcess.agendamento.CPF.$invalid,
                  invalid: $v.taskContext.agendamentoProcess.agendamento.CPF.$invalid,
                }"
                v-model="$v.taskContext.agendamentoProcess.agendamento.CPF.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.CEP')" for="preencher-dados-CEP">CEP</label>
              <input
                type="text"
                class="form-control"
                name="CEP"
                id="preencher-dados-CEP"
                data-cy="CEP"
                :class="{
                  valid: !$v.taskContext.agendamentoProcess.agendamento.CEP.$invalid,
                  invalid: $v.taskContext.agendamentoProcess.agendamento.CEP.$invalid,
                }"
                v-model="$v.taskContext.agendamentoProcess.agendamento.CEP.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.endereco')" for="preencher-dados-endereco"
                >Endereco</label
              >
              <input
                type="text"
                class="form-control"
                name="endereco"
                id="preencher-dados-endereco"
                data-cy="endereco"
                :class="{
                  valid: !$v.taskContext.agendamentoProcess.agendamento.endereco.$invalid,
                  invalid: $v.taskContext.agendamentoProcess.agendamento.endereco.$invalid,
                }"
                v-model="$v.taskContext.agendamentoProcess.agendamento.endereco.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.complemento')" for="preencher-dados-complemento"
                >Complemento</label
              >
              <input
                type="text"
                class="form-control"
                name="complemento"
                id="preencher-dados-complemento"
                data-cy="complemento"
                :class="{
                  valid: !$v.taskContext.agendamentoProcess.agendamento.complemento.$invalid,
                  invalid: $v.taskContext.agendamentoProcess.agendamento.complemento.$invalid,
                }"
                v-model="$v.taskContext.agendamentoProcess.agendamento.complemento.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.qtdVacinas')" for="preencher-dados-qtdVacinas"
                >Qtd Vacinas</label
              >
              <input
                type="number"
                class="form-control"
                name="qtdVacinas"
                id="preencher-dados-qtdVacinas"
                data-cy="qtdVacinas"
                :class="{
                  valid: !$v.taskContext.agendamentoProcess.agendamento.qtdVacinas.$invalid,
                  invalid: $v.taskContext.agendamentoProcess.agendamento.qtdVacinas.$invalid,
                }"
                v-model.number="$v.taskContext.agendamentoProcess.agendamento.qtdVacinas.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.preencherDados.sintomas')" for="preencher-dados-sintomas"
                >Sintomas</label
              >
              <input
                type="text"
                class="form-control"
                name="sintomas"
                id="preencher-dados-sintomas"
                data-cy="sintomas"
                :class="{
                  valid: !$v.taskContext.agendamentoProcess.agendamento.sintomas.$invalid,
                  invalid: $v.taskContext.agendamentoProcess.agendamento.sintomas.$invalid,
                }"
                v-model="$v.taskContext.agendamentoProcess.agendamento.sintomas.$model"
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

<script lang="ts" src="./preencher-dados-execute.component.ts"></script>
