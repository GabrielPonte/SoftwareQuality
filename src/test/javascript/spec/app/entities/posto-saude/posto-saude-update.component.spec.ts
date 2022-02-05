/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import PostoSaudeUpdateComponent from '@/entities/posto-saude/posto-saude-update.vue';
import PostoSaudeClass from '@/entities/posto-saude/posto-saude-update.component';
import PostoSaudeService from '@/entities/posto-saude/posto-saude.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('PostoSaude Management Update Component', () => {
    let wrapper: Wrapper<PostoSaudeClass>;
    let comp: PostoSaudeClass;
    let postoSaudeServiceStub: SinonStubbedInstance<PostoSaudeService>;

    beforeEach(() => {
      postoSaudeServiceStub = sinon.createStubInstance<PostoSaudeService>(PostoSaudeService);

      wrapper = shallowMount<PostoSaudeClass>(PostoSaudeUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          postoSaudeService: () => postoSaudeServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.postoSaude = entity;
        postoSaudeServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(postoSaudeServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.postoSaude = entity;
        postoSaudeServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(postoSaudeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundPostoSaude = { id: 123 };
        postoSaudeServiceStub.find.resolves(foundPostoSaude);
        postoSaudeServiceStub.retrieve.resolves([foundPostoSaude]);

        // WHEN
        comp.beforeRouteEnter({ params: { postoSaudeId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.postoSaude).toBe(foundPostoSaude);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
