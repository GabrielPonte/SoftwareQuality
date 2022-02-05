/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import PostoSaudeComponent from '@/entities/posto-saude/posto-saude.vue';
import PostoSaudeClass from '@/entities/posto-saude/posto-saude.component';
import PostoSaudeService from '@/entities/posto-saude/posto-saude.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('PostoSaude Management Component', () => {
    let wrapper: Wrapper<PostoSaudeClass>;
    let comp: PostoSaudeClass;
    let postoSaudeServiceStub: SinonStubbedInstance<PostoSaudeService>;

    beforeEach(() => {
      postoSaudeServiceStub = sinon.createStubInstance<PostoSaudeService>(PostoSaudeService);
      postoSaudeServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<PostoSaudeClass>(PostoSaudeComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          postoSaudeService: () => postoSaudeServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      postoSaudeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllPostoSaudes();
      await comp.$nextTick();

      // THEN
      expect(postoSaudeServiceStub.retrieve.called).toBeTruthy();
      expect(comp.postoSaudes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      postoSaudeServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removePostoSaude();
      await comp.$nextTick();

      // THEN
      expect(postoSaudeServiceStub.delete.called).toBeTruthy();
      expect(postoSaudeServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
