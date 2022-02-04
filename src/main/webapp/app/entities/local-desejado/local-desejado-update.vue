<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="softwareQualityApp.localDesejado.home.createOrEditLabel"
          data-cy="LocalDesejadoCreateUpdateHeading"
          v-text="$t('softwareQualityApp.localDesejado.home.createOrEditLabel')"
        >
          Create or edit a LocalDesejado
        </h2>
        <div>
          <div class="form-group" v-if="localDesejado.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="localDesejado.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('softwareQualityApp.localDesejado.localCovid')" for="local-desejado-localCovid"
              >Local Covid</label
            >
            <input
              type="text"
              class="form-control"
              name="localCovid"
              id="local-desejado-localCovid"
              data-cy="localCovid"
              :class="{ valid: !$v.localDesejado.localCovid.$invalid, invalid: $v.localDesejado.localCovid.$invalid }"
              v-model="$v.localDesejado.localCovid.$model"
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
            :disabled="$v.localDesejado.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./local-desejado-update.component.ts"></script>
