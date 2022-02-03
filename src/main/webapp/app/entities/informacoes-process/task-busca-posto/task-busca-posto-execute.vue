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
                v-text="$t('softwareQualityApp.taskBuscaPosto.nomeDoPosto')"
                for="task-busca-posto-nomeDoPosto"
                >Nome Do Posto</label
              >
              <input
                type="text"
                class="form-control"
                name="nomeDoPosto"
                id="task-busca-posto-nomeDoPosto"
                data-cy="nomeDoPosto"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.nomeDoPosto.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.nomeDoPosto.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.nomeDoPosto.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('softwareQualityApp.taskBuscaPosto.estadoDoPosto')"
                for="task-busca-posto-estadoDoPosto"
                >Estado Do Posto</label
              >
              <input
                type="text"
                class="form-control"
                name="estadoDoPosto"
                id="task-busca-posto-estadoDoPosto"
                data-cy="estadoDoPosto"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.estadoDoPosto.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.estadoDoPosto.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.estadoDoPosto.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('softwareQualityApp.taskBuscaPosto.cidadeDoPosto')"
                for="task-busca-posto-cidadeDoPosto"
                >Cidade Do Posto</label
              >
              <input
                type="text"
                class="form-control"
                name="cidadeDoPosto"
                id="task-busca-posto-cidadeDoPosto"
                data-cy="cidadeDoPosto"
                :class="{
                  valid: !$v.taskContext.informacoesProcess.informacoes.cidadeDoPosto.$invalid,
                  invalid: $v.taskContext.informacoesProcess.informacoes.cidadeDoPosto.$invalid,
                }"
                v-model="$v.taskContext.informacoesProcess.informacoes.cidadeDoPosto.$model"
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

<script lang="ts" src="./task-busca-posto-execute.component.ts"></script>
