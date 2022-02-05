<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="softwareQualityApp.postoSaude.home.createOrEditLabel"
          data-cy="PostoSaudeCreateUpdateHeading"
          v-text="$t('softwareQualityApp.postoSaude.home.createOrEditLabel')"
        >
          Create or edit a PostoSaude
        </h2>
        <div>
          <div class="form-group" v-if="postoSaude.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="postoSaude.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('softwareQualityApp.postoSaude.nomePosto')" for="posto-saude-nomePosto"
              >Nome Posto</label
            >
            <input
              type="text"
              class="form-control"
              name="nomePosto"
              id="posto-saude-nomePosto"
              data-cy="nomePosto"
              :class="{ valid: !$v.postoSaude.nomePosto.$invalid, invalid: $v.postoSaude.nomePosto.$invalid }"
              v-model="$v.postoSaude.nomePosto.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('softwareQualityApp.postoSaude.estadoPosto')" for="posto-saude-estadoPosto"
              >Estado Posto</label
            >
            <input
              type="text"
              class="form-control"
              name="estadoPosto"
              id="posto-saude-estadoPosto"
              data-cy="estadoPosto"
              :class="{ valid: !$v.postoSaude.estadoPosto.$invalid, invalid: $v.postoSaude.estadoPosto.$invalid }"
              v-model="$v.postoSaude.estadoPosto.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('softwareQualityApp.postoSaude.cidadePosto')" for="posto-saude-cidadePosto"
              >Cidade Posto</label
            >
            <input
              type="text"
              class="form-control"
              name="cidadePosto"
              id="posto-saude-cidadePosto"
              data-cy="cidadePosto"
              :class="{ valid: !$v.postoSaude.cidadePosto.$invalid, invalid: $v.postoSaude.cidadePosto.$invalid }"
              v-model="$v.postoSaude.cidadePosto.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.postoSaude.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./posto-saude-update.component.ts"></script>
